# 抽獎系統操作說明

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
   
# 演算法說明

1. 點選"開始抽獎"後，  
(1)建立size為總抽獎人數之ArrayList，透過Shuffle方法將其內存資料排序打亂(圖1.2)  
(2)取出打亂排序的ArrayList中，Index介於startIndex(先前獎項總人數)和[startIndex+prizePeople(此獎抽出人數)]  (圖1.3)  
(3)set 此次中獎人員資料  
(4)update 總抽獎人數、剩餘人數和抽獎日期至MAIN_FOR_WINNER Table

![image](https://user-images.githubusercontent.com/12302993/173704600-bca59f69-f762-41fd-b3bf-ae3988e9b9a0.png)
圖1.1  
![image](https://user-images.githubusercontent.com/12302993/173706914-e1425b8a-93dd-4adc-b19b-47f79ad36f7f.png)  
圖1.2
![image](https://user-images.githubusercontent.com/12302993/173706978-07b0b4bb-a083-4bd8-a65a-7c0420f60075.png)
圖1.3  

2. 將中獎人員、MainId、獎項、中獎日期等資料存入WINNER_LIST Table。set 剩餘人數和MainId於model中，並結束此獎項

![image](https://user-images.githubusercontent.com/12302993/173708223-f968dd3e-7134-4a36-9764-5f9edf1ef86c.png)  

3. 取得1.1提到打亂的ArrayList(57-64行)，並結續重複1和2之邏輯  
![image](https://user-images.githubusercontent.com/12302993/173708906-8ace35c2-0219-4f9b-aa55-20a4e279362d.png)








