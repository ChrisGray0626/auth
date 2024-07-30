package pers.ruizhi.course.exception;

import lombok.Getter;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String entity;
    private final Integer id;

    public EntityNotFoundException(String entity, Integer id) {
        super(String.format("Entity %s with id %d not found", entity, id));
        this.entity = entity;
        this.id = id;
    }
}
