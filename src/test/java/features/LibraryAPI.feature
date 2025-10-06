Feature: validating Library API

  Scenario Outline: Verify Book is Being Added or not Using AddBookAPI
    Given Add Book Payload Using "<SheetName>" and "<RowNum>"
    When user calls "AddBookAPI" with "POST" request
    Then the API call is success with status code "200"
#    And verify Author Name created maps to the "<SheetName>" "<RowNum>" using "GetBookAPI"
    Examples:
    | SheetName | RowNum |
    | Add Book  | 1      |
    | Add Book  | 2      |
    | Add Book  | 3      |
    | Add Book  | 4      |
    | Add Book  | 5      |

#  Scenario Outline: Verify Book is Being Deleted or not Using DeleteBookAPI
#    Given Delete Book Payload Using "<SheetName>" and "<RowNum>"
#    When user calls "DeleteBookAPI" with "POST" request
#    Then the API call is success with status code "200"
##    And verify Author Name created maps to the "<SheetName>" "<RowNum>" using "GetBookAPI"
#    Examples:
#      | SheetName | RowNum |
#      | Add Book  | 1      |
#      | Add Book  | 2      |
#      | Add Book  | 3      |
#      | Add Book  | 4      |
#      | Add Book  | 5      |

#  Scenario Outline: Verify Get Book by ID using GetBookAPI
#    Given Get Book Payload Using "<SheetName>" and "<RowNum>"
#    When user calls "GetBookAPI" with "GET" request
#    Then the API call is success with status code "200"
#    Examples:
#      | SheetName | RowNum |
#      | Places    | 1      |
#      | Places    | 2      |
#      | Places    | 3      |