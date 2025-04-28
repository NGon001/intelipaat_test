package org.Pages;

import org.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class Blog extends Base {
    private String BlogUrl = "https://intellipaat.com/blog/ai/";
    protected String cssSelectorCatDrop = "#catdropdown";
    protected String xpathCategoryName = "//*[@id=\"tutorial-single-left\"]/div[2]/h1";
    protected String xpathLeftTop = "//*[@class='cat-left-top']";
    protected String idTab = "cat-tab";
    protected String cssSelectorActivePane = ".tab-pane.active";
    protected String classPages = "page-numbers";
    protected String cssSelectorNextButton = ".next.page-numbers";
    protected String cssSelectorCoursesCount = ".col-lg-4.col-md-6";


    public void setupBlogPage() {
        driver.get(BlogUrl);
    }

    public List<String[]> getOptions(){
        Select dropDown = new Select(findWithWait(By.cssSelector(cssSelectorCatDrop)));
        List<WebElement> optionList = dropDown.getOptions();
        List<String[]> options = new ArrayList<>();
        for (WebElement option : optionList){
            options.add(new String[]{option.getText().trim(), option.getDomAttribute("data")});
        }
        return options;
    }

    public String getCategoryName(){
        return find(By.xpath(xpathCategoryName)).getText().trim();
    }

    public List<WebElement> getTabs(){ // get tabs in current category (Articles, Tutorials, etc...)
        return find(By.xpath(xpathLeftTop)).findElement(By.id(idTab)).findElements(By.xpath(".//a"));
    }

    public int getTabsCount(){
        return getTabs().size();
    }

    public WebElement getActivePane(){ // Active Content Wrapper (courses tabs, pages)
        return find(By.cssSelector(cssSelectorActivePane));
    }

    public boolean isNext(WebElement ActivePane){ // Check if next button is exist in current page
        try {
            if(ActivePane.findElement(By.cssSelector(cssSelectorNextButton)).isDisplayed())
                return true;
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public void clickNext(WebElement ActivePane){ // click next button
        ActivePane.findElement(By.cssSelector(cssSelectorNextButton)).click();
    }

    public int getPageCountOfCourses(WebElement ActivePane){ // Get count of courses in current page (maximum 15 courses in one page)
        return ActivePane.findElements(By.cssSelector(cssSelectorCoursesCount)).size();
    }

    public void clickNextTab(WebElement tab){ // click next tab (Articles, Tutorials, etc..)
        tab.click();
    }

    public int getCurseCount(WebElement currentTab){ // Get Courses count from tabs info (overall all courses count in all pages should be)
        return Integer.parseInt(currentTab.findElement(By.xpath(".//span")).getText().trim().replaceAll("[()]", ""));
    }

    public String getTabName(WebElement currentTab){ // returns Name of category
        return currentTab.getText().trim();
    }

    public void nextOption(String url){ // Go to next category
        driver.get(url);
    }
}
