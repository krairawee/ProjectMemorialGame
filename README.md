# แบบข้อเสนอโครงงาน

## ชื่อโครงงาน

เกมสืบสวนคดีฆาตรกรรม Memorial (Storyline Game)

## สมาชิก

1. นายไกรวี บุญทัด รหัสนิสิต 6730300051
2. นายกันต์พจน์ ฟ้าจงประเสริฐ รหัสนิสิต 6730300027

## รายละเอียดโดยย่อ

โครงงานนี้เป็นเกมแนวสืบสวนเนื้อเรื่อว 2 มิติ (2D Storyline Game) รูปแบบ Topdown ผสมผสานกับการดำเนินเนื้อเรื่องผ่าน Cutsence แบบ Visual Novel ซึ่งผู้เล่นจะรับบทเป็นนักเรียนธรรมดาคนนึงที่จู่ๆก็ถูกลักพาตัวมายังสถานที่ประหลาด โดยผู้เล่นสามารถขยับตัวละครเพื่อทำการสืบสวนเบาะแส แต่โดยพื้นฐานระบบหลักจะเกี่ยวกับการดำเนินเนื้อเรื่องผ่าน Cutsence และมี Choice ให้เลือกตอบหรือ Minigame ให้ทำเพื่อ Process เนื้อเรื่องจนจบเกม

## คุณลักษณะและขอบเขต

### คุณลักษณะขั้นต่ำ

- ตัวละครหลักเป็นนักเรียน ซึ่งสามารถควบคุมให้เคลื่อนที่ได้ 4 ทิศทาง โดยสามารถพูดคุยผ่านบทสนทนากับตัวละครตัวอื่นได้
- มี Dialog Choice พื้นฐานให้ผู้เล่นเลือกตอบตามเบาะแสที่มีใน Inventory  
- มีระบบ Minigame ในระหว่าง Cutsence อย่างน้อย 1 Minigame
- มีด่านที่สามารถเล่นได้อย่างน้อย 1 ด่าน (ส่วนใหญ่จะดำเนินเนื้อเรื่องผ่านศาลชั้นเรียนโดยการพูดและถกเถียงประเด็นในคดีฆาตรกรรม)
- มี Chapter ให้เล่นอย่างน้อย 1 Chapter
- มีระบบ Save/Load 

### คุณลักษณะเพิ่มเติม

- มีฉากสืบสวน Investigate เพิ่มขึ้นมาเช่น map ต่างๆ
- Minigame มีความหลากหลายมากขึ้น
- มีระบบเสียงพากย์
- มีระบบ UI ที่ดีมากขึ้น เช่น มี ภาพประกอบเบาะแสใน Inventory
- มี Chapter เพิ่มขึ้น เนื้อเรื่องยาวขึ้น

## แผนการดำเนินโครงงาน

| **ระยะ**        | **ช่วงเวลา**   | **ผลลัพธ์ที่คาดหวัง**                                                                            |
| --------------- | -------------- | ------------------------------------------------------------------------------------------------ |
| เริ่มต้นโครงงาน | สัปดาห์ที่ 1-2 | - ส่งข้อเสนอโครงงาน<br>- สร้าง GitHub repository<br>- สร้างโครงงานด้วย Maven และเขียนโค้ดตั้งต้น |
| ออกแบบโครงสร้างเนื้อเรื่อง     | สัปดาห์ที่ 3 | - มีแบบโครงสร้างเกม <br>- มีเนื้อเรื่องและบทพูดที่สมบูรณ์ <br>- มี assets ต่อเกม                  |
| สร้างต้นแบบ     | สัปดาห์ที่ 4-5 | - เกมทำงานได้ในเบื้องต้น<br>- มีฟีเจอร์หลัก (core feature) ที่ใช้งานได้ เช่น ระบบ Cutsence และระบบเนื้อเรื่องที่สมบูรณ์ <br>- มีแผนที่ในเกม                  |
| สร้างฟีเจอร์เสริม     | สัปดาห์ที่ 6 | - มีการต่อยอด Base feature พวก front end<br>- มีในส่วนของ Minigame ระบบเสริม|
| สร้างฐานข้อมูลและระบบบันทึกโหลด  | สัปดาห์ที่ 7 | - เกมมีฟีเจอร์ครบถ้วนตามที่วางแผน<br>- โค้ดไม่มีปัญหาหรือบัค <br>- มีระบบ save/load Game         |
| ทดสอบและพร้อมส่งมอบงาน  | สัปดาห์ที่ 8 | - เกมมีฟีเจอร์ครบถ้วนตามที่วางแผน<br>- เอกสารประกอบสมบูรณ์ พร้อมสำหรับการนำเสนอ          |

## การแบ่งงาน

- **นายไกรวี บุญทัด**:
    - ออกแบบโครงสร้างเนื้อเรื่อง และหา reference เช่น assets และ หนังสือนิยาย
    - เขียน Logic Base (backend) เช่น แผนที่หลัก และ ระบบ Cutsence 
    - จัดการ GitHub repository
    - เขียนระบบเสริม (backend) เช่น Minigame และ แผนที่
    - เขียน UI ของเกม (front end) เช่น หน้าต่าง dialog และ choice
    - เขียนฐานข้อมูลของเกมเพื่อ สามารถบันทึกเกมและโหลดเกม
- **นายกันต์พจน์ ฟ้าจงประเสริฐ**:
    - ช่วยเขียนเนื้อเรื่อง และออกแบบเนื้อหาเกม
    - ช่วยเขียน UI หน้า Interface เกม


## ความท้าทายและความเสี่ยง

1. **ความท้าทายด้านเทคนิค**:
    - การเขียน Game โดยใช้ภาษา Java และหลักการที่เกี่ยวข้องกับเกม เช่น ระบบ Physic ต่างๆ เป็นเรื่องใหม่สำหรับการทำงาน
    - **แนวทางแก้ไข**: ศึกษาข้อมูลภาษา Java ผ่านทาง Youtube หรือ เว็บไซต์ที่เกี่ยวข้อง
2. **ความท้าทายด้านการพัฒนาเกมด้วย FXGL**:
    - ต้องเรียนรู้ FXGL เพิ่มเติม เพราะเคยใช้แต่ JavaFX พื้นฐาน และมีระบบบางระบบที่ต้องเข้าใจรูปแบบการเขียนโค้ดของทาง FXGL เช่น ระบบ structure directory ของโค้ด
    - **แนวทางแก้ไข**: อ่าน Wiki ใน GitHub (https://github.com/AlmasB/FXGL/wiki/FXGL-11) และดู Youtube ศึกษาความรู้เกี่ยวกับ FXGL เพื่อพัฒนาความเข้าใจ
3. **ความท้าทายด้านการออกแบบเนื้อเรื่องและออกแบบแผนที่**:
    - บางครั้งเนื้อเรื่องในเกมอาจเกิดอุปสรรคหลายๆอย่างในการสร้างเกม เช่น ความเข้าใจเนื้อเรื่องในเกมยิ่งเป็นเกมที่มีเนื้อหาเป็นบทพูด, Cutsence และองค์ประกอบในฉากเป็นหลักในการดำเนินเนื้อเรื่องยิ่งต้องใช้ความรู้ทางด้านศิลปะเข้ามาช่วยด้วย
    - **แนวทางแก้ไข**: ศึกษาการวาดแผนที่ จาก internet และศึกษาเนื้อเรื่องผ่านทางการอ่านนิยายหรือเกม รวมถึงวิเคราะห์เนื้อหาที่จะใช้เป็นองค์ประกอบในเกม
4. **ความเสี่ยงด้านเวลา**:
    - หากใช้เวลาเรียนรู้ส่วนประกอบใหม่มากเกินไป อาจทำให้พัฒนาโค้ดไม่ทัน 
    - **แนวทางแก้ไข**: วางแผนฝึกทักษะคู่ไปกับการพัฒนาโครงงาน และติดตามความคืบหน้า (progress) 
5. **ความเสี่ยงด้านความสมบูรณ์ของโค้ด**:
    - ในบางครั้งหากมีการใส่ input ที่มีเงื่อนไข่ที่ไม่รองรับอาจเกิด bug หรือเกิด exception ในโค้ดได้
    - **แนวทางแก้ไข**: มีการทดลองระบบอยู่เสมอ และจัดโครงสร้างของระบบให้เกิดความสับสนน้อยที่สุด

## ต้นแบบและเอกสารอ้างอิง

- FXGL Tutorial: [https://github.com/AlmasB/FXGL/wiki/FXGL-11](https://github.com/AlmasB/FXGL/wiki/FXGL-11)
- JavaFX Documentation: [https://openjfx.io/](https://openjfx.io/)
- Story reference : [https://danganronpa.fandom.com/wiki/Danganronpa_V3:_Killing_Harmony]
- SystemGame reference : [https://store.steampowered.com/app/567640/Danganronpa_V3_Killing_Harmony/]
- Assets reference : [https://assetbakery.itch.io/horror-house-tileset]
