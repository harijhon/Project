using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Http.Cors;
using System.Web.Mvc;
using Gendarme.Models;

namespace Gendarme.Controllers
{
    public class plaintesController : Controller
    {
        private gendarmeEntities db = new gendarmeEntities();


        [EnableCors("http://localhost:8080", "*", "*")]
        [ActionName("List")]
        public ActionResult ListJson()
        {
            // Récupérez vos données (par exemple, depuis une base de données)
            var plaintes = db.plainte.ToList();

            // Ajoutez l'en-tête 'Access-Control-Allow-Origin' pour permettre les requêtes CORS depuis 'http://localhost:8080'
            Response.AppendHeader("Access-Control-Allow-Origin", "http://localhost:8080");

            return Json(plaintes, JsonRequestBehavior.AllowGet);
        }



        // GET: plaintes/Details/5
        [EnableCors("http://localhost:8080", "*", "*")]
        [Route("plaintes/Details/{id}")]
        public ActionResult Details(int? id)
        {
            Response.AppendHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            plainte plainte = db.plainte.Find(id);
            return Json(plainte, JsonRequestBehavior.AllowGet);
        }

        // POST: plaintes/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [EnableCors("http://localhost:8080", "*", "*")]
        [HttpPost]
        public ActionResult Create([Bind(Include = "idPlainte,idOmby,lieuPlainte,datePlainte,lieuResolution,dateResolution")] plainte plainte)
        {
            Response.AppendHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            db.plainte.Add(plainte);
            db.SaveChanges();
            return Redirect("http://localhost:8080/Affichage-war/Plainte?status=done&idOmby=" + plainte.idOmby);
        }

        [EnableCors("http://localhost:8080", "*", "*")]
        [HttpPost]
        [ActionName("Update")]
        [Route("plaintes/Update/{id}")]
        public ActionResult UpdatePlainte(int id, plainte updatedPlainte)
        {
            if (ModelState.IsValid)
            {
                var existingPlainte = db.plainte.Find(id);

                if (existingPlainte == null)
                {
                    return HttpNotFound(); // Ou une autre action de gestion d'erreur
                }

                // Mettez à jour les propriétés de l'entité existante avec les nouvelles valeurs
                existingPlainte.idPlainte = updatedPlainte.idPlainte;
                existingPlainte.idOmby = updatedPlainte.idOmby;
                existingPlainte.lieuPlainte = updatedPlainte.lieuPlainte;
                existingPlainte.datePlainte = updatedPlainte.datePlainte;
                existingPlainte.lieuResolution = updatedPlainte.lieuResolution;
                existingPlainte.dateResolution = updatedPlainte.dateResolution;

                // Indiquez à Entity Framework que cette entité a été modifiée
                db.Entry(existingPlainte).State = EntityState.Modified;

                // Enregistrez les modifications dans la base de données
                db.SaveChanges();

                return Redirect("http://localhost:8080/Affichage-war/Plainte");
            }

            return View(updatedPlainte);
        }


        // POST: plaintes/Delete/5
        [EnableCors("http://localhost:8080", "*", "*")]
        [HttpGet, ActionName("Delete")]
        [Route("ControllerName/Delete/{id}")]
        public ActionResult DeleteConfirmed(int id)
        {
            plainte plainte = db.plainte.Find(id);
            db.plainte.Remove(plainte);
            db.SaveChanges();
            return Redirect("http://localhost:8080/Affichage-war/Plainte");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
