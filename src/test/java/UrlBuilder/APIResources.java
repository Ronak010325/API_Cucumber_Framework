package UrlBuilder;

//enum is a special class in java which has collection of constants/methods
public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    AddBookAPI("/Library/Addbook.php"),
    GetBookAPI("/Library/GetBook.php"),
    DeleteBookAPI("/Library/DeleteBook.php"),
    FirstPrompt("/external/api/completion");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}