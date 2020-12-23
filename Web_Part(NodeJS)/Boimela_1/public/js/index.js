var firebaseConfig = {
    apiKey: "AIzaSyB5mcwTbNpUe9rCoU0dW2-atWneG6O9buc",
    authDomain: "softwareengproject-50239.firebaseapp.com",
    databaseURL: "https://softwareengproject-50239.firebaseio.com",
    projectId: "softwareengproject-50239",
    storageBucket: "softwareengproject-50239.appspot.com",
    messagingSenderId: "669309310911",
    appId: "1:669309310911:web:edbaf2e8440ab3413ff30a"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.auth.Auth.Persistence.LOCAL;

  function deletebookRecord(key)
  {
      var deleteRef=firebase.database().ref().child("AllBooks").child(key);
      return deleteRef.remove()
      .then(function()
      {
          console.log("Removed Successfully");
      })
      .catch(function(){
          console.log("Error occured");
      });
  }


  $("#btn-login").click(function()
    {
        var email=$("#email").val();
        var password=$("#password").val();
        if(email !="" && password !="")
        {
            
            var result=firebase.auth().signInWithEmailAndPassword(email,password);
            
            result.catch(function(error){
                var errorCode=error.code;
                var errorMessage=error.message;
                console.log(errorCode);   
                console.log(errorMessage);
                window.alert("Message : "+errorMessage );
            });
        }
        else
        {
            window.alert("Please fill out all fields.");
        }
    }
  
  );
  


  $("#btn-uploadBook").click(function()
  {
      var title=$("#title").val();
      var author=$("#author").val();
      var type=$("#type").val();
      var page=$("#page").val();
      var price=$("#price").val();
      var details=$("#bio").val();
      
      var rootRef= firebase.database().ref().child('AllBooks');
      var userID=firebase.auth().currentUser.uid;
      var userRef=rootRef.push();
      if(title!="" && author!="" && type!=""  && page!="" && price!="" && details!="")
      {
          
          
          
            var userData=
                {
                    "title": title,
                    "author" : author,
                    "type" : type,
                    "page": page,
                    "price": price,
                    "details": details,
                    "uid": firebase.auth().currentUser.uid,
                    
                };
                
                
                userRef.set(userData,function(error)
                {
                    
                    if(error)
                    {
                        
                        var errorCode=error.code;
                        var errorMessage=error.message;
                        console.log(errorCode);   
                        console.log(errorMessage);
                        window.alert("Message : "+errorMessage );
                    }
                    else
                    {
                        
                        window.location.href="uploadBook.html";
                    }
                });
                
      }
      else
      {
          window.alert("Form is incomplete");
      }
      
  });

  $("#btn-update").click(function()
  {
      var phone=$("#phone").val();
      var address=$("#address").val();
      var bio=$("#bio").val();
      var fName=$("#firstName").val();
      var sName=""
      var country=$("#country").val();
      var gender=""
      
      var rootRef= firebase.database().ref().child('Users');
      var userID=firebase.auth().currentUser.uid;
      var userRef=rootRef.child(userID);
      if(fName!="" && country!="" && phone!=""  && bio!="" && address!="")
      {
          window.alert(gender);
          
          
            var userData=
                {
                    "phone": phone,
                    "address" : address,
                    "bio" : bio,
                    "firstName": fName,
                    "secondName": sName,
                    "country": country,
                    
                };
                
                
                userRef.set(userData,function(error)
                {
                    
                    if(error)
                    {
                        
                        var errorCode=error.code;
                        var errorMessage=error.message;
                        console.log(errorCode);   
                        console.log(errorMessage);
                        window.alert("Message : "+errorMessage );
                    }
                    else
                    {
                        
                        window.location.href="MainPage.html";
                    }
                });
                
      }
      else
      {
          window.alert("Form is incomplete");
      }
      
  });

  
 $("#btn-signup").click(function()
    {
        var email=$("#email").val();
        var password=$("#password").val();
        var cpassword=$("#confirmPassword").val();
        
        if(email !="" && password !="" && cpassword!="")
        {
            
            if(password==cpassword)
            {
                
                
                window.alert("everything is ok");
                var result=firebase.auth().createUserWithEmailAndPassword(email,password);
            
            result.catch(function(error){
                var errorCode=error.code;
                var errorMessage=error.message;
                console.log(errorCode);   
                console.log(errorMessage);
                window.alert("Message : "+errorMessage );
            });
            }
            else
            {
                window.alert("Password didn't match with confirm password");
            }
        }
        else
        {
            window.alert("Please fill out all fields.");
        }
    }
  
  );

  $("#btn-resetPassword").click(function()
  {
      var auth=firebase.auth();
      var email=$("#email").val();
      
      if(email!="")
      {
            auth.sendPasswordResetEmail(email).then(function()
             {
                window.alert("Email has been sent to you,Please check and verify");
             }
            ).catch(function(error)
            {
                var errorCode=error.code;
                var errorMessage=error.message;
                console.log(errorCode);   
                console.log(errorMessage);
                window.alert("Message : "+errorMessage );
            });
      }
      else
      {
          window.alert("Please write your email first");
      }
  }

 );
 function switchView(view)
 {
     $.get({
         url:view,
         cache:false,
     })
     .then(function(data){
         $("#container").html(data);
     });
 }
 function gomyblogs()
 {
     window.location.href="myblogs.html"
 }