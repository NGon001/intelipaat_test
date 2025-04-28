package org.Pages;

import org.base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class Login extends Base {
    protected String LoginUrl = "https://intellipaat.com/signin/";
    protected String xpathForm = "//*[@class='lwa-form']";
    protected String xpathUsernameInput = xpathForm + "//p[contains(label,'Username')]//input";
    protected String xpathPasswordInput = xpathForm + "//p[contains(label,'Password')]//input";
    protected String idSubmitButton = "lwa_wp-submit";
    protected String xpathMessage = xpathForm + "//span";
    protected String xpathLoggedin = "//*[@class='loggedinuser']";


    public void setupLoginPage() {
        driver.get(LoginUrl);
    }

    public void clickSubmit(){
        click(By.id(idSubmitButton));
    }

    public void usernameInput(String username){
        input(By.xpath(xpathUsernameInput),username);
    }
    public void passwordInput(String password){
        input(By.xpath(xpathPasswordInput),password);
    }

    public String getMessage(){
        return find(By.xpath(xpathMessage)).getText().trim();
    }

    public boolean isLoggedIn(){
        try{
            findWithWait(By.xpath(xpathLoggedin));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
