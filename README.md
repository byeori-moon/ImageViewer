# ImageViewer 앱

## 프로젝트 설명

https://www.sooplive.co.kr/directory/category 에서 이미지를 가져와 썸네일을 그리드 형식으로 표시하는 앱입니다.
- [APK 다운로드 - v1.0.0](https://github.com/byeori-moon/ImageViewer/releases/tag/v1.0.0)


## 실행 방법

### 1. 환경 준비

- 이 프로젝트는 Android API 21 이상에서 실행됩니다.

### 2. 프로젝트 클론

- GitHub에서 프로젝트를 클론하거나, zip 파일을 다운로드하여 압축을 풉니다.
  ```bash
  git clone https://github.com/yourusername/ImageViewer.git

### 3. 의존성 설정
Glide 라이브러리를 프로젝트에 추가하려면 build.gradle 파일에 다음 의존성을 추가합니다:

  ```bash
  dependencies {
      implementation 'com.github.bumptech.glide:glide:4.13.0'
      annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
  }
  ```


# 기술 스택

- **Kotlin**: 앱 로직 구현
- **OkHttp**: API 요청을 보내고 데이터를 가져오기 위해 사용
- **Glide**: 이미지 로딩 및 캐싱을 위한 라이브러리
- **RecyclerView**: 이미지를 그리드 형식으로 표시
- **JSONObject**: JSON 데이터를 파싱하기 위해 사용

# 구현 기능 목록

1. **이미지 로드**: 주어진 API에서 이미지를 다운로드하여 화면에 표시합니다.
2. **메모리/디스크 캐시**: Glide 라이브러리를 사용하여 이미지 캐싱 기능을 구현합니다.
3. **그리드 레이아웃**: RecyclerView를 사용하여 이미지를 2열 또는 4열로 표시합니다.
4. **가로/세로 모드 지원**: 화면 회전에 따라 이미지 레이아웃을 자동으로 조정합니다.

# API

이미지 데이터는 개발자 도구를 통해 확인한 아래의 API를 통해 가져왔습니다.

**API URL**: [https://sch.sooplive.co.kr/api.php?m=categoryList](https://sch.sooplive.co.kr/api.php?m=categoryList)

## 응답 예시

```json
{
  "result": 1,
  "data": {
    "is_more": true,
    "offset": 120,
    "list": [
      {
        "category_no": "00130000",
        "category_name": "태그/게임",
        "cate_img": "https://admin.img.sooplive.co.kr/category_img/2024/10/15/7849670d90c7abaa8.png"
      },
      ...
    ]
  }
}


