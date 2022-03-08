package com.example.useroperationsapp.model;
import java.util.List;
import lombok.Data;

/**
 * Объект с фильтрами для запросов
 * */
@Data
public class FilterRequest<T extends FilterEnum> {
    /** список фильтров */
    private List<FilterContainer<T>> filters;
}
