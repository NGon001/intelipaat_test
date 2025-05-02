# 🧪 Intellipaat QA Automation Tests

This project demonstrates a basic QA automation test framework in Java using **TestNG**, **Selenium**, with both **API testing** and **UI testing** examples for the [Intellipaat](https://intellipaat.com) website. [Bug report](https://docs.google.com/document/d/1klm0EeJ2uhAAFSk0leF6QJtsLw22r85GXBqUiGUPcLA/edit?usp=drive_link)

---


## ❌ Example of Failed Tests

### Blog UI Testing:

An inconsistency was found during UI testing.  
The number of courses displayed in the left tab has changed, causing validation tests to fail.

| Screenshot                                                                                             | Description                                         |
|:-------------------------------------------------------------------------------------------------------|:----------------------------------------------------|
| ![Blog UI Failure](https://github.com/user-attachments/assets/6dbaa954-0f36-45a0-ac8e-35881e3d95d8)    | Main UI Issue Detected  Expected: 218,  Actual: 220 |
| ![Page 4](https://github.com/user-attachments/assets/6f4b7385-12c4-4876-9c7a-ef1a0b4fc516)             | Page 4 - expected result 218                        |
| ![Page 5 Differences](https://github.com/user-attachments/assets/ee6ec293-5fc0-42bf-9545-d1b47e447aec) | Difference in Left Tab, actual result is 220        |

---

## 📝 Conclusion

**Reason for Failure:**
> The amount of courses listed in the left-side tab changed, leading to test case failures.

---
