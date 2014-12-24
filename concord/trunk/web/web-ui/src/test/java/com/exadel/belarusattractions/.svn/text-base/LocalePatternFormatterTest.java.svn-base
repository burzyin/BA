package com.exadel.belarusattractions;

import com.exadel.belarusattractions.internationalization.LocaleValidator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:29 AM, 3/20/13
 */

public class LocalePatternFormatterTest extends AbstractTestClass {

    @Autowired
    private LocaleValidator localeValidator;

    @Test
    public void testFormPattern() throws Exception {
        Pattern pattern = LocalePatternFormatter.formPattern(localeValidator);
        Assert.assertNotNull(pattern);
    }
}
