# Tender2.0
[TEAM CHERRY] Alex Li, Alyssa Pham, David Jiang, and Jeongyeob Hong
# Description
Tender is a food-based application that parodies the famous online dating service Tinder<sup>TM</sup>. 
## How to Use
- Upon opening the app, you will be greeted with a welcome page displaying the app's theme colors and logo
- You will then be prompted to either sign-in to a pre-existing account or create a new one through your email
- Afterwards, you will then be redirected to the app's primary home page where you will have four options to select from:
  - **Getting Started:** A tutorial page that walks you through the features/allowable actions of the app
  - **Dietary Restrictions:** A page where you can check off personal dietary preferences that the app will then take into account when displaying dish options
  - **Relationship Type:** A page where you can choose whether you would like the app to display dish profiles that primarily offer takeout or sit-in options
- If you click the **next arrow (-->)** you will then be taken to the main activity of the app. Here, you will be presented with a dish profile and have the ability to **swipe left** to dislike the option or **swipe right** and like it.
  - Each swipe will take you to a new dish profile afterwards.
  - Profiles that you have swiped right to will automatically be saved in your matches page. You can access your match page any time while viewing dish profiles by clicking on the the bottom right button.
#  Project Status
- **Active** (until after the final probably)
- **Features we would have liked to implement:**
  - Usage of an API to better collect dish/restaurant information (unable to fully account for all dietary restrictions such as nut/fish allergies because of lack of reporting by restaurants)
  - Location as a user preference option (i.e find "matches" within a certain distance range)
  - Ability to log-out and reset password
  - Allow users to select the type of foods, so that the food suggestion could incorporate their cravings
  - Match animation rather than toast message
  - Ability to sort dishes using Firebase rather than hard-coding the dish information
# Contributing
- Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change. 
- **IMPORTANT NOTE:** All dish/restaurant information was curated by hand and stored in Firebase. Cloud Firestore and Realtime Database were also utilized in the creation of this application to store and access user preferences to better curate what dish profiles were to be viewable. 
- For extensive testing/changes, our Firebase repository is partially open to public; however, it is recommended you request access to our Firebase repository.
