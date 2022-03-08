package com.example.useroperationsapp.model;
import org.springframework.expression.spel.ast.Operator;
import lombok.NoArgsConstructor;
import java.util.Objects;
import java.util.List;
import lombok.Data;

/**
 * Фильтр для запроса (на будущее))
 * */
@Data
@NoArgsConstructor
public class FilterContainer<T extends FilterEnum> {
    /** фильтр по полю */
    private T filter;
    /** значения для фильтра */
    private List<String> values;
    /** оператор для фильтра */
    private Operator operator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterContainer<T> that = (FilterContainer<T>) o;
        return filter == that.filter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter);
    }

    public FilterContainer(T filter, List<String> values) {
        this.filter = filter;
        this.values = values;
    }

    public FilterContainer(T filter, List<String> values, Operator operator) {
        this.filter = filter;
        this.values = values;
        this.operator = operator;
    }
}