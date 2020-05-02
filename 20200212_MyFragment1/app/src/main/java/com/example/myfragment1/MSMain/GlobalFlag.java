package com.example.myfragment1.MSMain;

public class GlobalFlag {
    private Integer gWidth;
    private Integer gHeight;

    private boolean testIntentAddLocationFlag = false; //초기값은 이걸로 설정한다.

    private static GlobalFlag instance = null;

    public Integer getWidth()
    {
        return gWidth;
    }
    public void setWidth(Integer width)
    {
        this.gWidth = width;
    }
    public Integer getHeight()
    {
        return gHeight;
    }
    public void setHeight(Integer height)
    {
        this.gHeight = height;
    }

    //로케이션 세팅 값
    public void setIntentAddLocationFlag(){ //로케이션 에드 플레그 반대값으로 세팅
        this.testIntentAddLocationFlag = !testIntentAddLocationFlag; //false 시 true 로, true 시 false 로 변경한다.
    }
    public boolean getIntentAddLocationFlag(){ //현재 플래그 값 가져온다.
        return testIntentAddLocationFlag;
    }




    public static synchronized GlobalFlag getInstance(){

        if(null == instance){
            instance = new GlobalFlag();
        }
        return instance;
    }
   // 출처: https://purplecowd.tistory.com/entry/android-프로젝트-전체-전역변수-만들어-사용하는-방법

}
