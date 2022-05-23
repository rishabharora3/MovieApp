package com.personal.movieapp.utils;


import com.personal.movieapp.BaseTesting;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilUnitTest extends BaseTesting {

    @Test
    public void changeFormatTest() {
        description = "Change Date Format";
        String str = DateTimeUtil.INSTANCE.genericDateFormatter("2020-11-19", DateTimeUtil.date_YYYY_MM_DD,
                DateTimeUtil.date_MMM_DD_YYYY);
        assert str != null;
        Assert.assertTrue(str.equalsIgnoreCase("November 19, 2020"));
    }

}
