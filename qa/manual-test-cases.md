# Smart Contact CRM – Manual Test Cases

## TC01 – Open Login Page

**URL:** http://localhost:8082/login

**Steps:**
1. Open browser
2. Navigate to the login URL

**Expected Result:**
Login page should load successfully without errors

**Actual Result:**
Login page loaded successfully

**Status:** PASS

## TC02 – Open Register Page

**URL:** http://localhost:8082/register

**Steps:**
1. Open browser
2. Navigate to the register URL

**Expected Result:**
Register page should load successfully

**Actual Result:**
Register page loaded successfully

**Status:** PASS
## TC03 – User Registration with Valid Data

**URL:** http://localhost:8082/register

**Steps:**
1. Open register page
2. Enter valid user details
3. Click Register button

**Expected Result:**
User should be registered successfully and redirected to login or home page

**Actual Result:**
User registered successfully

**Status:** PASS
## TC04 – Login with Valid Credentials

**URL:** http://localhost:8082/login

**Steps:**
1. Open login page
2. Enter registered email
3. Enter correct password
4. Click Login button

**Expected Result:**
User should login successfully and redirect to home page

**Actual Result:**
User logged in and redirected to home page

**Status:** PASS
## TC05 – Access Contacts Without Login

**URL:** http://localhost:8082/contacts

**Steps:**
1. Open browser in Incognito mode
2. Navigate directly to contacts URL

**Expected Result:**
User should be redirected to login page

**Actual Result:**
User redirected to login page

**Status:** PASS

