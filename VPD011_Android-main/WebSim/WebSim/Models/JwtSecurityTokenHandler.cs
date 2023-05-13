public string GenerateJwtToken(User user)
{
    var securityKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(Configuration["JwtToken:Secret"]));
    var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

    var claims = new[]
    {
        new Claim(JwtRegisteredClaimNames.Sub, user.Id.ToString()),
        new Claim
