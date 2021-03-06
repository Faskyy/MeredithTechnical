//STEPS and then CODE is provided below
//1. Create a main test page that will automate the search function
//2. Create an allRecipesAction page to create the functions that our allRecipesPage will use
//3. Create an allRecipesPage that extends WebBasePage (this will handle click and enter functionalities)


testdata.properties =  [
    recipeName = Fahd's Fantastic Burger   
]

    
//assume we have a properties file that will hold values for what we'd want to automate
//above is some pseudocode


//-----------------

//import some external stuff things here

//we can create an allRecipesPage that would be referencing from an allRecipesAction page

//this is our allRecipesPage
//assume we have a WebBasePage that handles all of our click, enter, wait functionalities, etc...

    public class allRecipesPage extends WebBasePage {

    public allRecipesPage() {
        super("Allrecipes");
    }

//we want to get our properties (recipeName we want to enter, etc) by traveling to that directory
    private final static String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\testdata.properties";

//now we can define our property by getting it from our testdataproperties page
    String recipeName = prop.getProperty("recipeName");                                                                                  

//we can navigate to the allrecipes.com website
    driver = webdriver.Chrome();
    driver.get("https://allrecipes.com");

//once the page loads, we want to click on the findRecipe textbox. We can specify which one by xpath
    public void clickFindRecipeTextbox() {
        click(By.xpath("//div[@class='search-field']//textarea"), "Find a Recipe");  
    }

//then we want to enter what value we want to search for, thich would be Fahd's Fantastic Burger
    public void enterRecipeName() {
        enter(By.xpath("//div[@class='search-field']//textarea"), recipeName, "Recipe Name");
    }

//now we just want to hit the search button!
    public void clickSearchButton() {
        click(By.xpath("//section[@class='button searchButton']//button]"), "Search Button");
    }
}

//-----------------------
//this is our allRecipesAction page that will handle the different functions

public class allRecipesAction(){
//create a new allRecipesPage object

AllRecipesPage searchAllRecipes = new AllRecipesPage();

//we can just create a method that will call all the functions we made in our allRecipesPage
public void navigateToAllRecipes(){
    searchAllRecipes.clickFindRecipeTextbox();
    searchAllRecipes.enterRecipeName();
    searchAllRecipes.clickSearchButton();
  }
}

//your method navigateToAllRecipes() will be referenced from our main automation testing page, which we can just call Allrecipes.java

//-------------------------
//this is our MAIN PAGE that will test automate our methods

//assume we have a WebTestPage that serves the same purpose as our WebBasePage
public class Allrecipes extends WebTestBase {  

@Test()
public void allrecipesTest(){
    test = getTest("..."); //grabbing the test from our webtestpage
    allrecipesAction searchAction = new allrecipesAction();
    searchAction.navigateToAllRecipes(); //this is our method we created in our action page
  }
}
