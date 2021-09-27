# 당근마켓 클론 앱
당근마켓 안드로이드 앱을 공부 목적으로 클론 코딩한 저장소 입니다. 앱의 전체적인 기능 구현을 목표로 하기보단 담당한 파트를 공부하고 개발하고 있으며, 현재까지의 목표는 아래와 같습니다.<br>
|기능|담당|완료여부|
|:--|:--:|:--:|
|로그인 기능|[Park-SM](https://github.com/Park-SM)| O |
|메인페이지의 "홈" 기능|[Park-SM](https://github.com/Park-SM)| X |
|메인페이지의 "동네생활" 기능|[Lee-Jun-Young](https://github.com/Lee-Jun-Young)| X |

## 1. Git-flow
Vincent Driessen이 제안한 Git Flow를 활용 중입니다.
- main<br>
기준이 되는 브랜치로 제품을 배포하는 브랜치 입니다. 본 클론 앱에서는 일정 목표에 도달했을 때 합칩니다.
- dev<br>
개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 합칩니다.
- feature-[name]<br>
단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 dev 브랜치에 합칩니다.
- hotfix-[name]<br>
main 브랜치에서 발견한 버그를 수정하기 위해 사용합니다.


## 2. Server API
앱에서 사용되는 API는 NodeJs를 통해 구현했고 소스는 아래의 주소에서 확인할 수 있습니다.<br>
>https://github.com/Park-SM/DaangnMarket-API

## 3. Tech
`Android` `Kotlin` `MVVM` `ViewModel` `LiveData` `Coroutin` `DataBinding` `RepositoryPattern` `Dagger2` `Retrofit2` `OkHttpClient` `AccessToken` `RefreshToken` `Android-KTX` `GPS` `Paging3` `Glide` `CustomUI`

## 4. Preview
- 로그인 기능
https://user-images.githubusercontent.com/47319426/131224487-cce132b7-a3be-4a1d-abe3-df00d6b50797.mp4
