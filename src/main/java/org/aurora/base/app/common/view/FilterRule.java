package org.aurora.base.app.common.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRule {
    // op
    public static final String EQ = "eq";
    public static final String NE = "ne";
    public static final String GT = "gt";
    public static final String GE = "ge";
    public static final String LT = "lt";
    public static final String LE = "le";
    public static final String LIKE = "like";

    private String field;
    private String op;
    private Object value;
}
