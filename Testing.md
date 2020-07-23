# H4E1: Testing

## Problem Statement

1. Copy the results of the T03E01 Implementation repository into this repository so that you can continue with the same Java project.
2. Write at least 10 useful test cases
3. Include the mock object pattern at least in one test for not yet realized functionality.

**Important**:

- Make sure that your source code is consistent with the UML diagrams in your system design.
- You can extend the functionality started in T03 additionally to adding tests.
- Please note: In this exercise, there are no test cases. Your score will be inserted manually by one of the teaching assistants after the deadline of the exercise.

## Test Cases

### Shooting Bullets

1. When the Player shot a Bullet, verify Position of Bullet is near Position of Player. ✅
2. When the Player shot a Bullet, verify Direction of the Bullet (should be going *up*). ✅
3. When the Invader shot a Bullet, verify Position of Bullet is near Position of Invader. ✅
4. When the Invader shot a Bullet, verify Direction of the Bullet (should be going *down*). ✅

### GameBoard

5. When the GameBoard is initialized, no `Bullet`s should have been added yet. ✅
6. When Player is on the border of the screen and we move towards border, verify that Position doesn't change.

### Collision Detection

7. When two elements are in the same position, verify that the Collision detection equals to `true` ✅
8. When two elements are in different positions, verify that the Collision detection equals to `false` ✅

### Weapon Strategy

9. Test adding new weapons is working. ✅
10. Test getting the rank of the currently equipped weapon works ✅
11. Test that the weapon with the highest rank is selected with the default `WeaponPolicy`. ⚠️
