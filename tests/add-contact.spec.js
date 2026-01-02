const { test, expect } = require('@playwright/test');

test('Add new contact successfully', async ({ page }) => {

  // 1. Open login page
  await page.goto('http://localhost:8082/login');

  // 2. Login with VALID credentials (same as manual login)
  await page.fill('[name="username"]', 'hem');
  await page.fill('[name="password"]', '123456');
  await page.click('button[type="submit"]');

  // 3. Verify login by accessing protected page
  await page.goto('http://localhost:8082/contacts');
  await expect(page).not.toHaveURL(/login/);

  // 4. Go directly to Add Contact page
  await page.goto('http://localhost:8082/contacts/add');

  // 5. Fill contact form
  await page.fill('[name="name"]', 'Automation User');
  await page.fill('[name="email"]', 'auto@gmail.com');
  await page.fill('[name="phone"]', '9876543210');

  // 6. Save contact
  await page.click('button[type="submit"]');

  // 7. Verify contact added
  await expect(page.locator('text=Automation User')).toBeVisible();
});
