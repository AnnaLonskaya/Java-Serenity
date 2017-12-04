package com.dataart.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Alert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class BasicPage extends PageObject {

    public void acceptAlert() {
        waitForTimeout(2);
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    public void selectFromSuggestionList(String value, String listXpath) {
        int i = 1;
        boolean isFound = false;
        WebElementFacade row;
        int listSize = findAll(listXpath).size();
        while (i <= listSize && !isFound) {
            row = element(listXpath + "[" + i + "]/td[last()]");
            if (row.getText().startsWith(value)) {
                isFound = true;
                row.click();
            } else {
                i++;
            }
        }
        assertThat(isFound).isTrue();
    }

    public void selectByEndText(WebElementFacade select, String text) {
        int i = 0;
        boolean isFound = false;
        List<String> list;
        list = select.getSelectOptions();
        while (i < list.size() && !isFound) {
            if (list.get(i).endsWith(text)) {
                isFound = true;
                select.selectByIndex(i);
            } else {
                i++;
            }
        }
        assertThat(isFound).isTrue();
    }

    public void waitForTimeout(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkElementIsNotPresent(String xpath) {
        setImplicitTimeout(0, TimeUnit.SECONDS);
        Boolean isNotPresent = getDriver().findElements(By.xpath(xpath)).size() == 0;
        resetImplicitTimeout();
        return isNotPresent;
    }
}