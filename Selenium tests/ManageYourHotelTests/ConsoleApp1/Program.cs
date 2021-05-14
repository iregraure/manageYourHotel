using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using OpenQA.Selenium.Support.UI;

namespace ManageYourHotelTests
{
    class Program
    {
        static void Main(string[] args)
        {
            // Open the browser
            ChromeOptions options = new ChromeOptions();
            options.AddArgument("--start-maximized");
            IWebDriver driver = new ChromeDriver(options);
            driver.Url = "localhost:4200";

            // Log into the app
            driver.Navigate().GoToUrl("http://localhost:4200");
            // Wait untill the page is loaded
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            IWebElement username = wait.Until(e => e.FindElement(By.Id("username")));
            IWebElement password = driver.FindElement(By.Id("password"));
            Thread.Sleep(3000);
            username.Clear();
            password.Clear();
            Thread.Sleep(4000);
            username.SendKeys("ire");
            password.SendKeys("47812240R");
            Thread.Sleep(3000);
            IWebElement hidePass = driver.FindElement(By.Id("hide"));
            hidePass.Click();
            Thread.Sleep(4000);
            hidePass.Click();
            IWebElement loginButton = driver.FindElement(By.Id("logIn"));
            loginButton.Click();
            Thread.Sleep(5000);
            // Close browser
            driver.Close();
        }
    }
}
