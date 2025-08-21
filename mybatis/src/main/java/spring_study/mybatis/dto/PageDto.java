package spring_study.mybatis.dto;

import lombok.Getter;

@Getter
public class PageDto {
    private final int page;   // 'limit' -> 'page'로 변경
    private final int size;
    private final int offset;

    public PageDto(int page, int size) {
        this.page = page;
        this.size = size;
        this.offset = (page - 1) * size; // <-- 올바른 공식으로 변경
    }
}
