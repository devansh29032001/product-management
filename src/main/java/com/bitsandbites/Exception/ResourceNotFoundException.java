package com.bitsandbites.Exception;

public class ResourceNotFoundException extends RuntimeException {
		public ResourceNotFoundException(String msg) {
			super(msg);
		}
}

//1. Create Custom Exceptions
//One common exception (ResourceNotFoundException) is enough for both Product and Category.


