using System.Web.Http;
using System.Web.Http.Cors;

public static class WebApiConfig
{
    public static void Register(HttpConfiguration config)
    {
        // Configuration et routage de votre API Web.
        config.MapHttpAttributeRoutes();

        config.Routes.MapHttpRoute(
            name: "DefaultApi",
            routeTemplate: "api/{controller}/{id}",
            defaults: new { id = RouteParameter.Optional }
        );

        // Configuration CORS : autoriser les requêtes depuis "http://localhost:8080"
        var cors = new EnableCorsAttribute("http://localhost:8080", "*", "*");
        config.EnableCors(cors);
    }
}
