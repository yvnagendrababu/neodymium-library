package com.xceptance.neodymium.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Selenide;

public class HighlightAndWait
{
    private static final String injectJS;
    static
    {
        try (InputStream inputStream = HighlightAndWait.class.getResourceAsStream("inject.js"))
        {
            injectJS = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not load inject.js", e);
        }
    }

    public static void injectHighlightingJs()
    {
        if (Context.get().configuration.debuggingHighlightSelectedElements())
        {
            injectJavaScript();
        }
    }

    public static void highlightAllElements(By by, WebDriver driver)
    {
        if (Context.get().configuration.debuggingHighlightSelectedElements())
        {
            List<WebElement> foundElements = driver.findElements(by);
            highlightElements(foundElements, driver);
            if (Context.get().configuration.debuggingHighlightDuration() > 0)
            {
                Selenide.sleep(Context.get().configuration.debuggingHighlightDuration());
            }
            resetAllHighlight();
        }
    }

    public static void resetHighlights()
    {
        if (Context.get().configuration.debuggingHighlightSelectedElements())
        {
            resetAllHighlight();
        }
    }

    static void injectJavaScript()
    {
        Selenide.executeJavaScript(injectJS);
    }

    static void highlightElements(List<WebElement> elements, WebDriver driver)
    {
        long highlightTime = Context.get().configuration.debuggingHighlightDuration();
        if (highlightTime <= 0)
        {
            highlightTime = 75;
        }
        Selenide.executeJavaScript("if(window.NEODYMIUM){"
                                   + "window.NEODYMIUM.highlightAllElements(arguments[0], document, "
                                   + highlightTime + ");"
                                   + "}",
                                   elements, driver.getWindowHandle());
    }

    static void resetAllHighlight()
    {
        Selenide.executeJavaScript("if(window.NEODYMIUM){window.NEODYMIUM.resetHighlightElements(document);}");
    }
}
