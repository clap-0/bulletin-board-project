# Bulletin Board Project

> 게시판 웹 어플리케이션

## 프로젝트 소개 및 목적

### 프로젝트 소개
+ Bulletin Board Project는 Spring Boot, MySQL, MyBatis를 사용하여 구현한 REST API 기반의 게시판 웹 어플리케이션입니다. 
+ 게시글에 대한 CRUD(Create, Read, Update, Delete) 기능을 제공합니다.

### 프로젝트 목적
+ 이론으로만 배운 백엔드 지식들을 활용해보고, 문제 해결 능력을 기르고자 만들게 되었습니다.

### 사용 기술
+ Java 11
+ Spring Boot 2.4.1
+ MyBatis 2.3.0
+ MySQL
+ Thymeleaf

## 구조 및 설계

### 패키지 구조
<details>
<summary>펼치기</summary>

```
💾src
 ┣ 📁main
 ┃ ┣ 📁java
 ┃ ┃ ┗ 📁com.soo0.bulletin_board
 ┃ ┃ ┃ ┗ 📄BulletinBoardApplication
 ┃ ┃ ┃ ┗ 📁controller
 ┃ ┃ ┃ ┃ ┗ 📄BoardController
 ┃ ┃ ┃ ┃ ┗ 📄PostController
 ┃ ┃ ┃ ┃ ┗ 📄UserController
 ┃ ┃ ┃ ┃ ┗ 📄PageController
 ┃ ┃ ┃ ┗ 📁domain
 ┃ ┃ ┃ ┃ ┗ 📁dto
 ┃ ┃ ┃ ┃ ┃ ┗ 📄LoginRequest
 ┃ ┃ ┃ ┃ ┃ ┗ 📄SignupRequest
 ┃ ┃ ┃ ┃ ┃ ┗ 📄PostListRequest
 ┃ ┃ ┃ ┃ ┃ ┗ 📄PostSaveRequest
 ┃ ┃ ┃ ┃ ┃ ┗ 📄PostListResponse
 ┃ ┃ ┃ ┃ ┃ ┗ 📄PostResponse
 ┃ ┃ ┃ ┃ ┗ 📁vo
 ┃ ┃ ┃ ┃ ┃ ┗ 📄Board
 ┃ ┃ ┃ ┃ ┃ ┗ 📄BoardInfo
 ┃ ┃ ┃ ┃ ┃ ┗ 📄Post
 ┃ ┃ ┃ ┃ ┃ ┗ 📄PostInfo
 ┃ ┃ ┃ ┃ ┃ ┗ 📄User
 ┃ ┃ ┃ ┃ ┃ ┗ 📄UserInfo
 ┃ ┃ ┃ ┗ 📁exception
 ┃ ┃ ┃ ┃ ┗ 📄BoardNotFoundException
 ┃ ┃ ┃ ┃ ┗ 📄DataModificationException
 ┃ ┃ ┃ ┃ ┗ 📄DuplicateUserException
 ┃ ┃ ┃ ┃ ┗ 📄PostNotFoundException
 ┃ ┃ ┃ ┃ ┗ 📄UserNotFoundException
 ┃ ┃ ┃ ┃ ┗ 📄ErrorCode
 ┃ ┃ ┃ ┃ ┗ 📄ErrorCodeSupport
 ┃ ┃ ┃ ┃ ┗ 📄ErrorResponse
 ┃ ┃ ┃ ┃ ┗ 📄ExceptionControllerAdvice
 ┃ ┃ ┃ ┗ 📁mapper
 ┃ ┃ ┃ ┃ ┗ 📄BoardMapper
 ┃ ┃ ┃ ┃ ┗ 📄PostMapper
 ┃ ┃ ┃ ┃ ┗ 📄UserMapper
 ┃ ┃ ┃ ┗ 📁service
 ┃ ┃ ┃ ┃ ┗ 📄BoardService
 ┃ ┃ ┃ ┃ ┗ 📄BoardServiceImpl
 ┃ ┃ ┃ ┃ ┗ 📄PostService
 ┃ ┃ ┃ ┃ ┗ 📄PostServiceImpl
 ┃ ┃ ┃ ┃ ┗ 📄UserService
 ┃ ┃ ┃ ┃ ┗ 📄UserServiceImpl
 ┃ ┃ ┃ ┃ ┗ 📄SpringSecurityConfig
 ┃ ┣ 📁resources
 ┃ ┃ ┗ 📁mybatis
 ┃ ┃ ┃ ┗ 📁mapper
 ┃ ┃ ┃ ┃ ┗ 📄boardMapper.xml
 ┃ ┃ ┃ ┃ ┗ 📄postMapper.xml
 ┃ ┃ ┃ ┃ ┗ 📄userMapper.xml
 ┃ ┃ ┃ ┗ 📄mybatis-config.xml
 ┃ ┃ ┗ 📁static.assets
 ┃ ┃ ┃ ┗ 📁css
 ┃ ┃ ┃ ┗ 📁js.jquery
 ┃ ┃ ┗ 📁templates
 ┃ ┃ ┃ ┗ 📁fragments
 ┃ ┃ ┃ ┗ 📁views
 ┃ ┃ ┗ 📄application.yml
 ┃ ┃ ┗ 📁Resource Bundle 'messages'
 ┃ ┃ ┃ ┗ 📄messages.properties
 ┗ ┗ ┗ ┗ 📄messages_ko_KR.properties
 ```
</details>

### Database 설계
![database](https://user-images.githubusercontent.com/50361496/222459419-b0b8c404-9e5b-47d6-a6ff-81bc4e9eefcd.png)

### API 설계
<image src="https://user-images.githubusercontent.com/50361496/222447771-a2a1fb65-176c-4fff-92cf-9d38f219268f.png" width="400" height="280" />
<image src="https://user-images.githubusercontent.com/50361496/222448801-69fad92e-86b5-4565-a1c9-0ec21cf12223.png" width="400" height="280" />
<image src="https://user-images.githubusercontent.com/50361496/222449157-5ea94919-6ba7-4138-9877-a4bf0ef4e0ed.png" width="400" height="180" />
