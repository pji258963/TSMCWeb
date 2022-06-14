# 抽獎系統操作說明

抽獎系統URL: http://114.32.27.40:8080/mainService/findNowPrize

1.	登入頁面，設定總抽獎人數、總共獎項，並點選開始抽獎

   ![image](https://user-images.githubusercontent.com/12302993/173503103-419102d8-e281-44d0-8e47-35ae56311dbb.png)
  
2. 設定人數後點選抽獎，將中獎人員(假設為1-60000號之抽獎券)存入資料庫以利後續查詢，並跳轉至下一獎項。
   ![image](https://user-images.githubusercontent.com/12302993/173503317-32e12b87-9c20-4339-ab76-d8c4f09d06e9.png)
 
3.	依序抽出剩餘之獎項

   ![image](https://user-images.githubusercontent.com/12302993/173503449-513deff4-5075-4f0d-b418-a3d0690e56ce.png)
   ![image](https://user-images.githubusercontent.com/12302993/173503466-3c28c7f9-d848-4455-862d-0bd4b26712bb.png)
   ![image](https://user-images.githubusercontent.com/12302993/173508352-1a374285-263c-496e-ba2b-13855f9b174b.png)


4.	抽獎結束，依據需求選擇”查詢最新(此次)抽獎紀錄”、”歷史今日中獎記錄”或”重新抽獎”

   ![image](https://user-images.githubusercontent.com/12302993/173503541-e80bfae5-86a0-4bce-a1a4-7318d4434fb3.png)

5.	查詢最新(此次)抽獎紀錄

![image](https://user-images.githubusercontent.com/12302993/173508320-2d258659-c1cd-4cf4-980a-4b7dfdbe8f64.png)

6. 查詢歷史中獎記錄

   ![image](https://user-images.githubusercontent.com/12302993/173503670-02dfe203-2678-4a32-82a4-e7696f699e79.png)

7.	重新抽獎

   ![image](https://user-images.githubusercontent.com/12302993/173503704-ef3f3ad5-a164-4119-baef-82b5a60e99f4.png)

8.	DB設計
   MAIN_FOR_WINNER
   
   ![image](https://user-images.githubusercontent.com/12302993/173503773-c59c8e2b-1b4b-42fc-afe6-018d551ab571.png)
   WINNER_LIST
   
   ![image](https://user-images.githubusercontent.com/12302993/173503794-95d6edea-6d7d-430f-ad91-21ce7cd38d02.png)
