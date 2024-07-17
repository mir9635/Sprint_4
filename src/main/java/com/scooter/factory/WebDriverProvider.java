package com.scooter.factory;


import org.openqa.selenium.WebDriver;

public interface WebDriverProvider {
    WebDriver createDriver();
}
