package com.example.useroperationsapp.service;

import com.example.useroperationsapp.entity.UserEntity;
import com.example.useroperationsapp.mapper.PhoneMapper;
import com.example.useroperationsapp.mapper.ProfileMapper;
import com.example.useroperationsapp.mapper.UserMapper;
import com.example.useroperationsapp.model.UserDto;
import com.example.useroperationsapp.model.UserResponse;
import com.example.useroperationsapp.repository.PhoneRepository;
import com.example.useroperationsapp.repository.ProfileRepository;
import com.example.useroperationsapp.repository.SetQueryParams;
import com.example.useroperationsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, SetQueryParams {
    private final UserMapper userMapper;
    private final PhoneMapper phoneMapper;
    private final ProfileMapper profileMapper;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final ProfileRepository profileRepository;

    private static final String USER_EXIST = "Найден юзер по id: ";
    private static final String USER_UPDATED = "Обновлен юзер по id: ";
    private static final String USER_NOT_EXIST = "Такого юзера не существует!";
    private static final String ACCOUNT = "Счет пользователя с id: ";
    private static final String ACCOUNT_INCREASED = " увеличен на 10%!";
    private static final String ACCOUNT_BLOCKED_TO_INCREASE = "Счет пользователя с id: ";
    private static final String FIELDS_ENTERING_ERROR = "Введены не все обязательные поля!";
    private static final String USER_CREATION_ERROR = "Не возможно создать юзера - нет входных данных!";
    private static final String USER_DELETED = "Удален юзер с id: ";
    private static final String E_MAIL_OR_PHONE_ALREADY_EXISTS = "Юзер не создан так как уже существует юзер с таким e-mail или телефоном: ";
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Override
    @Transactional
    public UserDto saveNewUser(UserDto userRequest) {
        if (Objects.isNull(userRequest)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, USER_CREATION_ERROR);
        }
        UserEntity savedUser;
        try {
            var newUserToSave = userMapper.map(userRequest);
            newUserToSave.setProfile(null);
            savedUser = userRepository.save(newUserToSave);
            log.info("Создан новый юзер: " + savedUser);
        } catch (Exception e) {
            log.info(E_MAIL_OR_PHONE_ALREADY_EXISTS);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, E_MAIL_OR_PHONE_ALREADY_EXISTS);
        }

        var profileToSave = profileMapper.map(userRequest.getProfile());
        profileToSave.setUserEntity(savedUser);
        profileToSave.setInitialCash(userRequest.getProfile().getCash());
        profileToSave.setAbleToIncrease(true);
        profileRepository.save(profileToSave);

        var phonesToSave = userRequest.getUserPhones().stream().map(phoneMapper::map).collect(Collectors.toList());
        phonesToSave.forEach(phone -> phone.setUserEntity(savedUser));
        phoneRepository.saveAll(phonesToSave);

        return userRequest;
    }

    @Override
    public UserResponse updateUser(Long userId, UserDto dto) {
        if (Objects.isNull(dto.getUserPhones())
                || Objects.isNull(dto.getProfile())
                || Objects.isNull(dto.getAge())
                || Objects.isNull(dto.getEMail())
                || Objects.isNull(dto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FIELDS_ENTERING_ERROR);
        }
        var foundedUser = userRepository.findById(userId);
        if (foundedUser.isPresent()) {
            log.info(USER_EXIST + userId);
            var updatedUser = userRepository.saveAndFlush(userMapper.map(dto));
            log.info(USER_UPDATED + userId);
            return userMapper.mapToResponse(updatedUser);
        } else {
            log.error(USER_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, USER_NOT_EXIST);
        }
    }

    @Override
    @Scheduled(cron = "0/20 * * * * ?")
    public void increaseUserCashOnTenPercents() {
        var profiles = profileRepository.findAllByAbleToIncreaseTrue();
        profiles.forEach(profile -> {
            if (calcPercentageValue(profile.getCash(), 10).compareTo(calcPercentageValue(profile.getInitialCash(), 107)) < 0) {
                profile.setCash(profile.getCash().add(calcPercentageValue(profile.getCash(), 10)));
                log.info(ACCOUNT + profile.getUserEntity().getId() + ACCOUNT_INCREASED);
            } else {
                profile.setAbleToIncrease(false);
                log.info(ACCOUNT + profile.getUserEntity().getId() + ACCOUNT_BLOCKED_TO_INCREASE);
            }
        });
        profileRepository.saveAllAndFlush(profiles);
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            log.info(USER_DELETED + userId);
        } catch (Exception e) {
            log.error(USER_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, USER_NOT_EXIST);
        }
    }

    @Override
    public UserResponse getById(Long userId) {
        var foundedUser = userRepository.findById(userId);
        if (foundedUser.isPresent()) {
            log.info(USER_EXIST + userId);
            return userMapper.mapToResponse(foundedUser.get());
        } else {
            log.error(USER_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, USER_NOT_EXIST);
        }
    }

    public static BigDecimal calcPercentageValue(BigDecimal input, Integer percentage) {
        return input.multiply(BigDecimal.valueOf(percentage)).divide(ONE_HUNDRED);
    }

    @Override
    public List<UserResponse> getAll() {
        var userResponses = userRepository.findAll().stream().map(userMapper::mapToResponse).collect(Collectors.toList());
        var phones = phoneRepository.findAll().stream().map(phoneMapper::mapToResponse).collect(Collectors.toList());
        userResponses.forEach(user -> user.setUserPhones(phones.stream().filter(phone -> Objects.equals(user.getId(), phone.getUserId())).collect(Collectors.toList())));
        return userResponses;
    }

    @Override
    public Page<UserResponse> getAllUserPageable(Pageable pageable, Integer age, String phone, String name, String eMail) {
        if (Objects.isNull(age) && Objects.isNull(phone) && Objects.isNull(name) && Objects.isNull(eMail)) {
            var allUsers = userRepository.findAll(pageable);
            var allUserResponses = userRepository.findAll(pageable).stream().map(userMapper::mapToResponse).collect(Collectors.toList());
            return new PageImpl<>(allUserResponses, pageable, allUsers.getTotalElements());
        } else {
            var userEntityPage = userRepository.findAll(getUserSpecification(age, phone, name, eMail), pageable);
            var users = userEntityPage.stream().map(userMapper::mapToResponse).collect(Collectors.toList());
            return new PageImpl<>(users, pageable, userEntityPage.getTotalElements());
        }
    }

    public Specification<UserEntity> getUserSpecification(Integer age, String phone, String name, String eMail) {
        return (Specification<UserEntity>) (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            setParamQueryInteger(predicates, root, builder, "age", age);
            setParamQueryStrLike(predicates, root, builder, "phone", phone);
            setParamQueryStrLike(predicates, root, builder, "name", name);
            setParamQueryStrLike(predicates, root, builder, "eMail", eMail);
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}