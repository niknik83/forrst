Forrst
======
forrst is a Java library for the Forrst API(v2). Currently this library is in development and will provide basic support for reading posts. forrst has minimal dependencies and is aimed to be easy to use.

Android
-------
Android platform to be tested soon!

Build & Use
-----------
Each API method in this library is heavily commented. The descriptions are taken directly from Forrst. Please check the official [Forrst API documentation](http://forrst.com/api) for up to date API definitions and descriptions.
To begin, build Forrst and import it in your code. __To run the tests for the `users/auth`, `notifications` and `post/comments` endpoints update your credentials in `ForrstAPIClientTests.java`.__

    $ ant

    import com.forrst.api;

    ForrstAPI forrst = new ForrstAPIClient();

    // API stats
    forrst.stats();
    
    // Authentication
    forrst.usersAuth("EMAIL_OR_USERNAME", "PASSWORD");
    
    // Notifications
    forrst.notifications("ACCESS_TOKEN", options)

    // User information
    forrst.usersInfo("USERNAME");
    
    // User posts
    forrst.userPosts("USERNAME", options)

Note that each library API endpoint returns a JSONObject. For more information on JSONObject visit [json.org/java](http://json.org/java/). The dependency json-java jar file is also included in the `lib` folder.
Also, at the moment the rate limit threshold is soft, which means that it is up to the client application to ensure that it sticks close to the 150 calls/hour limit. This limit may become strictly
enforced eventually.

Details for the rest of the available API endpoints are given inline.

Contribute
------------
Submit well documented code with unit tests and I will merge your changes in
as long as your code does not break the build. Here are some things that need
to be done:

- refactor and remove redundancy
- Return full json
- Create ForrstException
- Rate limit API methods
- all authenticated API calls 
- rename API methods to something simpler
- fix broken ForrstAPIClient endpoints/tests
- Helper method to get all endpoints as Map<String,String>
- Decouple/refactor code
- Add some tests using optional params
- rename properties in buildfile
- maybe cache stuff?
- "tobeFixed" tests

Forrst API Endpoints
--------------------
This library is built around the Forrst API version 2. At the moment there are 8 API endpoints available and each of them uses `https://forrst.com/api/v2/` as the base URI. Also at the moment, API calls are rate limited to 150
calls per hour, so keep that in mind when designing your applications.

- **stats**
  - Returns stats about your API usage. Note: does not count against your rate limit.
  - `https://forrst.com/api/v2/stats`

- **notifications** (coming soon)
  - Return notification items for the authenticating user. Most items have an associated redirect URL that will take the user to the appropriate post/comment/etc. and clear the associated notification. Construct the URLs using the view_url_format format (provided in the response), replacing ID with the ID of the desired notification. Also note that not every type of notification (currently for likes, comments [replies, subscription-based, on your post], mentions, jobs, and follows) will have the same fields present for data.
  - `https://forrst.com/api/v2/notifications`

- **users/auth**
  - User authentication. Provide an email/username and password and get an access token back
  - `https://forrst.com/api/v2/users/auth`

- **users/info**
  - Returns user info
  - `https://forrst.com/api/v2/users/info`

- **user/posts**
  - Returns a user's posts
  - `https://forrst.com/api/v2/user/posts`

- **posts/show**
  - Return data about a single post. Note: For questions, content is the question. For code, content contains the code snippet. For code, snaps, and links, description is the post description; it is not used for questions.
  - `https://forrst.com/api/v2/posts/show`

- **posts/all**
  - Returns a list of all posts in reverse-chron order
  - `https://forrst.com/api/v2/posts/all`

- **posts/list**
  - Returns a list of posts of a given type
  - `https://forrst.com/api/v2/posts/list`

- **post/comments**
  - Returns a post's comments
  - `https://forrst.com/api/v2/post/comments`

Authors
-------

Our aim is to make Forrst integration into Java applications as easy as possible. We have tried our best to provide in-code documentation for each method.
Tests coming up soon! Until then please help us find bugs.

- Nitin Dhar

  - Forrst: https://forrst.com/people/nitindhar7
  - Site: http://coding-sense.blogspot.com
  - Twitter: @nitin_dhar

- Udi Mosayev

  - Forrst: https://forrst.com/people/udiudi
  - Site: http://udiudi.com
  - Twitter: @udiudi

Copyright
---------
Copyright (c) 2011 Nitin Dhar & Udi Mosayev. See LICENSE for details.