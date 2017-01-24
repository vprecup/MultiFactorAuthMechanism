using System;
using System.Net;
using Microsoft.AspNetCore.Mvc;

namespace OneTimePassword.Service.Controllers
{
    [Route("tapi/[controller]")]
    public class TokenController : Controller
    {
        [HttpGet("GetToken/{userEmailAddress}")]
        public string GetToken([FromRoute]string emailAddress)
        {
            //var decodedEmail = WebUtility.UrlDecode(emailAddress);
            return Program.TokenClient.GeneratePassword(emailAddress);
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
}