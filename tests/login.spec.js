const { test, expect } = require('@playwright/test');

test('Login with valid credentials', async ({ page }) => {

  // 1. Open login page
  await page.goto('http://localhost:8082/login');

  // 2. Enter email
  await page.fill('[name="username"]', 'hem');

  // 3. Enter password
  await page.fill('[name="password"]', '123456');
  // 4. Click login button
  await page.click('button[type="submit"]');

  // 5. Verify redirect to home page
  await expect(page).toHaveURL(/home/);
});
