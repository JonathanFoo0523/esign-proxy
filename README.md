# esign-proxy

This is a helper application for the React application `esign-mobile`.

Most web browser enforces CORS policy and will restrict React application from loading content from external site unless the response contain the 
appropriate header. Since we dont have access to ebiko server, in order
load resources from ebiko site [http://demo.ag-icloudsolutions.com], this application is created to act as a proxy server that load content from ebiko, add 
 `allow-content-origin:*` to the header before forwarding the response to esign-application.

Additionally, `Glue.java` provide additional functionality to the `esign-mobile` by providing a seamless transition for users who wish to sign the document on their 
mobile device.
