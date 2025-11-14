# CardioCoach - Powered By Strava

CardioCoach is an application that combines your Strava data with OpenAI's intelligence. It allows you to fetch your workout data via the Strava API and interact with ChatGPT directly to gain insights, analyses, and personalized advice based on your activity.

## 🚀 Features

- Strava API Integration – Fetch your cycling ~~, running, and other activity~~ data.
- Chat with CardioCoach – Ask questions about your workouts and get instant answers.
- Personalized Insights – Analyze pace, heart rate, distance, and trends over time.
- Data-Driven Coaching – Receive suggestions for improvement and training goals.

---

## Get Started

### .env Template
```dotenv
chatgpt.api.key=
chatgpt.api.baseUrl=https://api.openai.com/v1

# Strava webClient
strava.api.baseUrl=https://www.strava.com/api/v3
strava.api.authUrl=https://www.strava.com

# Strava ENV
strava.access-token=
strava.refresh-token=
strava.client-id=
strava.client-secret=
```

### Strava

1. Create an API application on [Strava API settings](https://www.strava.com/settings/api).
2. Authorize your app by opening this URL (replace YOUR_CLIENT_ID with your client ID):
```
http://www.strava.com/oauth/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=http://localhost/exchange_token&approval_prompt=force&scope=read_all,activity:read_all,profile:read_all
```
3. Click Authorize. You will be redirected to a URL like:
```
http://localhost/exchange_token?state=&code=YOUR_CODE&scope=read,activity:read_all,profile:read_all,read_all
```
4. Copy the YOUR_CODE from the URL.
5. Exchange the code for an access token and refresh token using Postman.
```
https://www.strava.com/oauth/token?client_id=&client_secret=&code=&grant_type=
```
6. Store your Strava credentials in your `.env` file.

### ChatGPT

1. Create a key on [OpenAI API Platform](https://platform.openai.com/api-keys)
2. Store the credential in your `.env` file.

Now your CardioCoach app is ready to fetch Strava data and interact with ChatGPT!
