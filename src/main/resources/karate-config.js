function fn() {    
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
	myVarName: 'someValue'
  }
  if (env == 'dev') {
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'e2e') {
    // customize
  }

  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);

  //Working Example
  var configMapSuccess = {"test" : "initial"};
  karate.log("configMapSuccess BEFORE adding project = ", configMapSuccess)
  configMapSuccess['project'] = {name: "DEMO PROJECT"}
  karate.log("configMapSuccess AFTER adding project = ", configMapSuccess)


  // Not working example
  var JavaDemo = Java.type('com.JavaDemo');
  var jd = new JavaDemo();
  var configMapFailure = karate.toJson(jd.doWork("fromJS"));
  karate.log("configMapFailure BEFORE adding project = ", configMapFailure)
  configMapFailure['project'] = {name: { a : { b : "test"}}}
  karate.log("configMapFailure AFTER adding project = ", configMapFailure.project.name.a.b)

  return config;
}