Feature: validating Library API

  @Master @AddBook
  Scenario Outline: Verify Book is Being Added or not Using AddBookAPI
    Given Add Book Payload Using "<SheetName>" and "<RowNum>"
    When user calls "AddBookAPI" with "POST" request
    Then the API call is success with status code "200"
    And "Msg" in response body is "<Message>"
    Examples:
      | SheetName     | RowNum | Message            |
      | Book Details  | 1      | successfully added |
      | Book Details  | 2      | successfully added |
      | Book Details  | 3      | successfully added |
      | Book Details  | 4      | successfully added |
      | Book Details  | 5      | successfully added |

  @Master @GetBookByID
  Scenario Outline: Verify Get Book by ID using GetBookAPI
    Given Get Book By ID Payload Using "<SheetName>" and "<RowNum>"
    When user calls "GetBookAPI" with "GET" request
    Then the API call is success with status code "200"
    And verify if the author Name maps to the original data using "<SheetName>" and "<RowNum>"
    Examples:
      | SheetName       | RowNum |
      | Book Details    | 1      |
      | Book Details    | 2      |
      | Book Details    | 3      |
      | Book Details    | 4      |
      | Book Details    | 5      |

  @Master @GetBookByAuthorName
  Scenario Outline: Verify Get Book by Name using GetBookAPI
    Given Get Book By Name Payload Using "<SheetName>" and "<RowNum>"
    When user calls "GetBookAPI" with "GET" request
    Then the API call is success with status code "200"
    Examples:
      | SheetName       | RowNum |
      | Book Details    | 1      |
      | Book Details    | 2      |
      | Book Details    | 3      |
      | Book Details    | 4      |
      | Book Details    | 5      |

  @Master @DeleteBook
  Scenario Outline: Verify Book is Being Deleted or not Using DeleteBookAPI
    Given Delete Book Payload Using "<SheetName>" and "<RowNum>"
    When user calls "DeleteBookAPI" with "POST" request
    Then the API call is success with status code "200"
    And "msg" in response body is "<Message>"
    Examples:
      | SheetName    | RowNum | Message                      |
      | Delete Book  | 1      | book is successfully deleted |
      | Delete Book  | 2      | book is successfully deleted |
      | Delete Book  | 3      | book is successfully deleted |
      | Delete Book  | 4      | book is successfully deleted |
      | Delete Book  | 5      | book is successfully deleted |