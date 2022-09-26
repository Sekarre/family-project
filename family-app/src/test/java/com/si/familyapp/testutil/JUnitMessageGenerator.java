package com.si.familyapp.testutil;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JUnitMessageGenerator<FROM, TO> {

    private Class<FROM> fromClass;
    private Class<TO> toClass;

    public String getMessage(String fromFieldName, Object fromValue, String toFieldName, Object toValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("Field `");
        if (fromClass != null) {
            sb.append(fromClass.getSimpleName()).append(".");
        }
        sb.append(fromFieldName).append("` with value `").append(fromValue).append("` doesn't match field `");

        if (toClass != null) {
            sb.append(toClass.getSimpleName()).append(".");
        }
        sb.append(toFieldName).append("` with value `").append(toValue).append("`");
        return sb.toString();
    }
}