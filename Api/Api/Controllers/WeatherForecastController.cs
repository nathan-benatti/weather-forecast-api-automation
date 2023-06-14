using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static List<WeatherForecast> weatherForecasts = new List<WeatherForecast>();

        private readonly ILogger<WeatherForecastController> _logger;

        public WeatherForecastController(ILogger<WeatherForecastController> logger)
        {
            _logger = logger;
        }

        [HttpGet(Name = "GetWeatherForecast")]
        public List<WeatherForecast> Get()
        {
            return weatherForecasts;
        }

        [HttpPost(Name = "CreateWeatherForecast")]
        public IActionResult Create(int temperatureC, string summary)
        {
            var weatherForecast = new WeatherForecast
            {
                Date = DateTime.Now,
                TemperatureC = temperatureC,
                Summary = summary
            };

            weatherForecasts.Add(weatherForecast);

            return Ok(weatherForecast);
        }

        [HttpDelete("{summary}", Name = "DeleteWeatherForecast")]
        public IActionResult Delete(string summary)
        {
            var deletedForecast = weatherForecasts.FirstOrDefault(x => x.Summary == summary);

            if (deletedForecast == null)
            {
                return NotFound();
            }

            weatherForecasts.Remove(deletedForecast);

            return Ok(deletedForecast);
        }

        [HttpPut("{summary}", Name = "UpdateWeatherForecast")]
        public IActionResult Update(string summary, [Bind("TemperatureC,Summary,Date")] WeatherForecast updatedForecast)
        {
            var existingForecast = weatherForecasts.FirstOrDefault(f => f.Summary == summary);

            if (existingForecast == null)
            {
                return NotFound();
            }

            existingForecast.TemperatureC = updatedForecast.TemperatureC;
            existingForecast.Summary = updatedForecast.Summary;
            existingForecast.Date = updatedForecast.Date;

            return Ok(existingForecast);
        }
    }
}