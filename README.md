# 아롬 Spring 14-6 쇼핑몰 API 프로젝트 입니다
## 개요
> 이 API는 유저 정보 삽입·조회, 상품 추가·조회·제거·주문·주문 취소, 카테고리 추가·제거, 장바구니 상품 추가·제거 등의 기능을 지원합니다. 

## 목표
> ERD를 참고하여 여러 기능과 엔드포인트를 가진 API를 개발할 수 있습니다

## 전체 구조
```plaintext
java
└── com.example.AlomShoppingmall
    ├── config
    │   ├── SecurityConfig
    │   └── WebConfig
    ├── controller
    │   ├── CartController
    │   ├── OrderController
    │   ├── ProductController
    │   └── UserController
    ├── dto
    │   ├── CartRequest
    │   ├── CartResponse
    │   ├── OrderRequest
    │   ├── OrderResponse
    │   ├── ProductCategoryResponse
    │   ├── ProductRequest
    │   ├── ProductResponse
    │   ├── UserRequest
    │   └── UserResponse
    ├── exception
    │   ├── CartNotFoundException
    │   ├── CategoryNotFoundException
    │   ├── DuplicateCategoryException
    │   ├── DuplicateEmailException
    │   ├── GlobalExceptionHandler
    │   ├── InsufficientStockException
    │   ├── OrderNotFoundException
    │   ├── ProductNotFoundException
    │   └── UserNotFoundException
    ├── model
    │   ├── Cart
    │   ├── Order
    │   ├── Product
    │   ├── ProductCategory
    │   └── User
    ├── repository
    │   ├── CartRepository
    │   ├── OrderRepository
    │   ├── ProductCategoryRepository
    │   ├── ProductRepository
    │   └── UserRepository
    ├── service
    │   ├── CartService
    │   ├── OrderService
    │   ├── ProductService
    │   └── UserService
    └── AlomShoppingmallApplication
```

## ERD
<p align="left">
  <img width="500" src="https://github.com/user-attachments/assets/de0aa684-8b45-4f36-abbb-ccc3167a28a9">
</p>

## API endpoint
| 엔드포인트                                  | 메서드 | 설명                   | 비고                                                                                                                                                               |
|---------------------------------------------|--------|------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /user                                       | POST   | 유저 정보 삽입          |                                                                                                                                                                    |
| /user?id=1&username=lorem&email=arom@naver.com | GET    | 유저 정보 가져오기      | - user.username<br>- user.email<br>- user.id<br>로 유저 정보 가져오기                                                                                             |
| /product                                    | POST   | 상품 추가               |                                                                                                                                                                    |
| /product                                    | GET    | 상품 목록 조회           | - 아무 조건 없이 검색 → 전체 상품 리스트<br>- product.id 검색 → 해당 id를 가진 상품 정보 반환                                                                       |
| /product                                    | DELETE | 상품 제거               |                                                                                                                                                                    |
| /product/category                           | POST   | 카테고리 추가           |                                                                                                                                                                    |
| /product/category                           | DELETE | 카테고리 제거           |                                                                                                                                                                    |
| /order                                      | POST   | 상품 주문               |                                                                                                                                                                    |
| /order                                      | DELETE | 주문 취소               |                                                                                                                                                                    |
| /order?order_id=1&order_date=2024-10-29&user_id=arom&product_id=1 | GET    | 주문 정보 가져오기      | - order.id<br>- order.order_date<br>- user.id<br>- product.id<br>혹은 해당 값을 중 여러 값을 넣고 AND 조건으로 주문 정보 가져오기 |
| /cart                                       | POST   | 장바구니에 상품 추가하기 | (한번에 1개씩만 추가 가능)                                                                                                                                        |
| /cart?user_id=1&product_id=1&product_category_id=1 | GET    | 장바구니 정보 가져오기  | - user.id<br>- product.id<br>- product_category.id<br>혹은 해당 값을 중 여러 값을 넣고 AND 조건으로 주문 정보 가져오기                                           |
| /cart                                       | DELETE | 장바구니에서 상품 제거   | (한번에 여러 개 가능)                                                                                                                                              |

## 기타 정보 

>(사용 라이브러리)

Spring Web

Spring Data JPA

MySQL Driver 

Validation

Spring Boot DevTools 

Lombok 

Spring Security 

Spring Cloud OpenFeign 

...
