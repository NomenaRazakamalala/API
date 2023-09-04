/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

   var putMethod = ( event ) => {
     // Prevent redirection of Form Click
     event.preventDefault();
     var target = event.target;
     while ( target.tagName != "FORM" ) {
       target = target.parentElement;
     } // While the target is not te FORM tag, it looks for the parent element
     // The action attribute provides the request URL
     var url = target.getAttribute( "action" );

     // Collect Form Data by prefix "put_" on name attribute
     var bodyForm = target.querySelectorAll( "[name^=put_]");
     var body = {};
     bodyForm.forEach( element => {
       // I used split to separate prefix from worth name attribute
       var nameArray = element.getAttribute( "name" ).split( "_" );
       var name = nameArray[ nameArray.length - 1 ];
       if ( element.tagName != "TEXTAREA" ) {
         var value = element.getAttribute( "value" );
       } else {
       // if element is textarea, value attribute may return null or undefined
         var value = element.innerHTML;
       }
       // all elements with name="put_*" has value registered in body object
       body[ name ] = value;
     } );
     var xhr = new XMLHttpRequest();
     xhr.open( "PUT", url );
     xhr.setRequestHeader( "Content-Type", "application/json" );
     xhr.onload = () => {
       if ( xhr.status === 200 ) {
       // reload() uses cache, reload( true ) force no-cache. I reload the page to make "redirects normal effect" of HTML form when submit. You can manipulate DOM instead.
         location.reload( true );
       } else {
         console.log( xhr.status, xhr.responseText );
       }
     }
     xhr.send( body );
   }

   var deleteMethod = ( event ) => {
     event.preventDefault();
     var confirm = window.confirm( "Certeza em deletar este conteúdo?" );
     if ( confirm ) {
       var target = event.target;
       while ( target.tagName != "FORM" ) {
         target = target.parentElement;
       }
       var url = target.getAttribute( "action" );
       var xhr = new XMLHttpRequest();
       xhr.open( "DELETE", url );
       xhr.setRequestHeader( "Content-Type", "application/json" );
       xhr.onload = () => {
         if ( xhr.status === 200 ) {
           location.reload( true );
           console.log( xhr.responseText );
         } else {
           console.log( xhr.status, xhr.responseText );
         }
       }
       xhr.send();
     }
   }
