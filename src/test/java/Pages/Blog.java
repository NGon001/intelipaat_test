package Pages;

import org.base.InitTests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Blog extends InitTests {

    @BeforeMethod
    public void openBlogPage(){
        blog.setupBlogPage();
    }

    //1 test total, 1 min 2 sec
    //===============================================
    //Default Suite
    //Total tests run: 1, Passes: 1, Failures: 0, Skips: 0
    //===============================================
    @Test
    public void testBlogsCourseCount_UI() {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();

        ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + methodName);
        ((JavascriptExecutor) driver).executeScript("sauce:job-build=_" + methodName + "_Build_2025_04_29");

        try {
            var blogOptions = blog.getOptions(); // Get all blog options: Python, Web, etc.
            for (String[] blogOption : blogOptions) {
                blog.nextOption(blogOption[1]); // Go to next option

                var tabs = blog.getTabs(); // Get tabs like Articles, Tutorials, etc.
                int tabCount = tabs.size(); // How many tabs in this category

                for (int i = 0; i < tabCount; i++) {
                    var activePane = blog.getActivePane(); // .tab-pane.active — get active content

                    int actualCount = 0; // How many really courses it is
                    int expectedCourseCount = blog.getCurseCount(tabs.get(i)); // How many courses should be on active tab

                    do {
                        Assert.assertEquals(blog.getCurseCount(tabs.get(i)), expectedCourseCount, "Failed at: " + blogOption[0] + " at tab: " + blog.getTabName(tabs.get(i))); // checks if number of curses changed in tab
                        actualCount += blog.getPageCountOfCourses(activePane);
                        if (!blog.isNext(activePane))
                            break; // check if next button is exist, if no, last page, and exit.
                        blog.clickNext(activePane);
                        activePane = blog.getActivePane(); // Refresh reference
                        tabs = blog.getTabs(); // Update tabs due to possible DOM change
                    } while (true);


                    Assert.assertEquals(actualCount, expectedCourseCount, "Failed at: " + blogOption[0] + " at tab: " + blog.getTabName(tabs.get(i))); // check if expected course count is the same as actual course count;


                    if ((tabCount - 1) > i) {
                        blog.clickNextTab(tabs.get(i + 1)); // Click on next tab (Articles, Tutorials, etc..)
                        tabs = blog.getTabs(); // Re-fetch after tab change
                    }
                }
            }
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + "passed");
        }catch (AssertionError e){
            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + "failed");
            throw e;
        }

    }
}