package net.itattractor.features;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.uispec4j.Window;

import java.util.List;

public class MyStepdefs {
    Adapter adapter = new Adapter();
    private Window loginWindow;
    private Window tasksWindow;
    private Window recordWindow;
    private String sentComment;

    WebDriver webDriver;
    private String lastComment;

    @Given("^I run application$")
    public void I_run_application() throws Throwable {
        loginWindow = adapter.getMainWindow();
        Assert.assertEquals(loginWindow.getTitle(), "login form");
    }

    @cucumber.api.java.en.When("^I input url \"([^\"]*)\" and username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void I_input_url_and_username_and_password(String url, String username, String password) throws Throwable {
        loginWindow.getInputTextBox("url").setText(url);
        loginWindow.getInputTextBox("username").setText(username);
        loginWindow.getPasswordField().setPassword(password);
    }

    @And("^I click submit button$")
    public void I_click_submit_button() throws Throwable {
        loginWindow.getButton("submit").click();
    }

    @Then("^I should see tasks form$")
    public void I_should_see_tasks_form() throws Throwable {
        tasksWindow = adapter.getTasksWindow();
        Assert.assertEquals(tasksWindow.getTitle(), "tasks form");
    }

    @And("^I click \"([^\"]*)\" button$")
    public void I_click_button(String name) throws Throwable {
         tasksWindow.getButton(name).click();
    }

    @And("^I should see \"([^\"]*)\"$")
    public void I_should_see(String expected) throws Throwable {
        recordWindow = adapter.getRecordWindow();
        String actual = recordWindow.getTitle();
        Assert.assertEquals(expected, actual);
    }

    @And("^I send comment \"([^\"]*)\" and submit$")
    public void I_send_comment_and_submit(String comment) throws Throwable {
        sentComment = comment;
        recordWindow.getInputTextBox("Label").setText(comment);
        recordWindow.getButton("ok").click();
    }

    @And("^I go to track \"([^\"]*)\" and username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void I_go_to_track_and_username_and_password(String url, String username, String password) throws Throwable {
        webDriver = new FirefoxDriver();
        String newUrl = "http://" + username + ":" + password + "@" + url;
        webDriver.navigate().to(newUrl);
        List<WebElement> change = webDriver.findElements(By.className("change"));
        if (change.size() > 0){
            String lastElement = change.get(change.size() - 3).getText();
            lastComment = lastElement.substring(lastElement.lastIndexOf('\n') + 1, lastElement.length()).trim();
        }
        Assert.assertEquals(sentComment, lastComment);
    }
}
