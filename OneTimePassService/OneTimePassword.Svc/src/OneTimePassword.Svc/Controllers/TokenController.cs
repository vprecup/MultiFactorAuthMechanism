using System;
using System.Net;
using Microsoft.AspNetCore.Mvc;
using System.Runtime.Serialization;

namespace OneTimePassword.Svc.Controllers
{
    [Route("tapi")]
    public class TokenController : Controller
    {
        [HttpGet("GetToken/{userEmailAddress}")]
        public IActionResult GetToken([FromRoute]string emailAddress)
        {
            //var decodedEmail = WebUtility.UrlDecode(emailAddress);
            return Json(new Token
            {
                Value = Program.TokenClient.GeneratePassword(emailAddress)
            });
        }

        [HttpGet("ChallengeToken/{userEmailAddress}/{token}")]
        public IActionResult ChallengeToken([FromRoute]string emailAddress, [FromRoute]string token) 
        {
            if (Program.TokenServer.ValidatePassword(token, emailAddress))
            {
                return Ok();
            }

            return Forbid();
        }
    }

    [DataContract]
    public class Token
    {
        [DataMember(Name = "token")]
        public string Value { get; set; }
    }
}