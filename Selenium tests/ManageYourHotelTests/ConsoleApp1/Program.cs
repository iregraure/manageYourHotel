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
            options.EnableMobileEmulation("Moto G4");
            IWebDriver driver = new ChromeDriver(options);
            driver.Url = "localhost:4200";
            driver.Navigate().GoToUrl("http://localhost:4200");

            hotelManagement(driver);
            clientApp(driver);
            
            // Close browser
            driver.Close();
        }

        private static void login(IWebDriver driver, string username, string password)
        {
            // Wait untill the page is loaded and get the "username" and "password" fields
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            IWebElement usernameInput = wait.Until(e => e.FindElement(By.Id("username")));
            IWebElement passwordInput = driver.FindElement(By.Id("password"));
            // Clear fields if they have something written
            usernameInput.Clear();
            passwordInput.Clear();
            // Log into the app
            usernameInput.SendKeys(username);
            passwordInput.SendKeys(password);
            Thread.Sleep(1000);
            IWebElement loginButton = driver.FindElement(By.Id("logIn"));
            loginButton.Click();
        }

        private static void hotelManagement(IWebDriver driver)
        {
            // Login into the app
            login(driver, "ire", "47812240R");
            // Go to client management and click the "New client" button
            Thread.Sleep(2000);
            IWebElement clientButton = driver.FindElement(By.Id("clientManagement"));
            Thread.Sleep(1000);
            clientButton.Click();
            Thread.Sleep(2000);
            IWebElement newClientButton = driver.FindElement(By.Id("newClient"));
            newClientButton.Click();
            // Fill the form and create de client
            Thread.Sleep(2000);
            //IWebElement nameInput = driver.FindElement(By.Id("name"));
            //IWebElement surnameInput = driver.FindElement(By.Id("surname"));
            //IWebElement dniInput = driver.FindElement(By.Id("dni"));
            //IWebElement emailInput = driver.FindElement(By.Id("email"));
            //IWebElement addressInput = driver.FindElement(By.Id("address"));
            //IWebElement phoneInput = driver.FindElement(By.Id("phone"));
            //IWebElement birthdateInput = driver.FindElement(By.Id("birthDate"));
            //IWebElement createButton = driver.FindElement(By.Id("create"));
            //nameInput.SendKeys("Selenium");
            //surnameInput.SendKeys("Automated");
            //dniInput.SendKeys("11122233E");
            //emailInput.SendKeys("seleniumautomated@gmail.com");
            //addressInput.SendKeys("5th Selenium street");
            //phoneInput.SendKeys("666999888");
            //birthdateInput.SendKeys("16/06/1985");
            //Thread.Sleep(2000);
            //createButton.Click();
            //Thread.Sleep(2000);
            //// Go to room management
            //IWebElement goHome = driver.FindElement(By.Id("goHome"));
            //goHome.Click();
            //IWebElement rooms = driver.FindElement(By.Id("roomManagement"));
            //rooms.Click();
            //Thread.Sleep(2000);
            //// Search for the room and see its details
            //IWebElement building = driver.FindElement(By.Id("Building2"));
            //building.Click();
            //Thread.Sleep(2000);
            //IWebElement floor = driver.FindElement(By.Id("2"));
            //floor.Click();
            //Thread.Sleep(2000);
            //IWebElement room = driver.FindElement(By.Id("201"));
            //room.Click();
            //Thread.Sleep(2000);
            //// Click on the "Add stay" button and fill the form to add a stay for the new client
            //IWebElement addStayButton = driver.FindElement(By.Id("addStay"));
            //addStayButton.Click();
            //Thread.Sleep(2000);
            //IWebElement startDate = driver.FindElement(By.Id("startDate"));
            //IWebElement endDate = driver.FindElement(By.Id("endDate"));
            //IWebElement clientDni = driver.FindElement(By.Id("clientDni"));
            //IWebElement addButton = driver.FindElement(By.Id("addStay"));
            //startDate.SendKeys("01/07/2021");
            //endDate.SendKeys("07/07/2021");
            //clientDni.SendKeys("11122233E");
            //Thread.Sleep(2000);
            //addButton.Click();
            // Log out of the app
            IWebElement logOut = driver.FindElement(By.Id("logOut"));
            logOut.Click();
            Thread.Sleep(2000);
            IWebElement confirm = driver.FindElement(By.Id("acceptConfirm"));
            confirm.Click();
            Thread.Sleep(2000);
        }

        private static void clientApp(IWebDriver driver)
        {
            // Login into the app
            login(driver, "11122233E", "11122233E");
            Thread.Sleep(2000);
            // Access the user management and change the user's password
            IWebElement user = driver.FindElement(By.Id("userManagement"));
            user.Click();
            Thread.Sleep(2000);
            IWebElement userDetails = driver.FindElement(By.Id("updateUser"));
            userDetails.Click();
            Thread.Sleep(2000);
            IWebElement password = driver.FindElement(By.Id("password"));
            IWebElement update = driver.FindElement(By.Id("update"));
            password.Clear();
            password.SendKeys("Selenium");
            Thread.Sleep(2000);
            update.Click();
            Thread.Sleep(2000);
            // Back to main page and check the stay
            IWebElement goHome = driver.FindElement(By.Id("goHome"));
            goHome.Click();
            Thread.Sleep(3000);
            IWebElement stayManagement = driver.FindElement(By.Id("staysManagement"));
            stayManagement.Click();
            Thread.Sleep(2000);
            IWebElement startDate = driver.FindElement(By.Id("201"));
            startDate.Click();
            Thread.Sleep(2000);
            // Log out of the app
            IWebElement logOut = driver.FindElement(By.Id("logOut"));
            logOut.Click();
            Thread.Sleep(2000);
            IWebElement confirm = driver.FindElement(By.Id("acceptConfirm"));
            confirm.Click();
            Thread.Sleep(2000);
        }

    }
}
