package org.aurora.base.app.common.view;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private long page;
    private long size;
    private long total;
    private List<T> rows;

    public static <T> PageVO<T> of(Page<T> p) {
        PageVO<T> vo = new PageVO<>();
        vo.page = p.getCurrent();
        vo.size = p.getSize();
        vo.total = p.getTotal();
        vo.rows = p.getRecords();
        return vo;
    }
}
