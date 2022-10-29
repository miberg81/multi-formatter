# multi-formatter
A spring boot REST api which gets an sql query and format(SVN/JSON) via a POST request
and returns a query result in a file of request format
The result is written to a file on disc and also returned  to the client in response.
The api is configured to work against multiple dataSources:
- when a request for a CSV file is received - the query runs in H2 DB.
- when a request for a JSON file is received - the query runs in SQLITE DB.
Each time the CSV/JSON parameter changes in the request - the db in use is switched.
These dbs are configured in application-dev.yaml (for DEV env)

For example:
for a post request to
http://localhost:8080/print
{
    "query": "select * from employer",
    "fileFormat": "CSV"
}
expected result is a scv file generated in "src\main\resources\employees.csv"
with the content as follows
id,firstName,lastname,address
1,firstName1,lastName1,H2
2,firstName2,lastName2,H2
3,firstName3,lastName3,H2
4,firstName4,lastName4,H2

for a post request to
http://localhost:8080/print
{
    "query": "select * from employer",
    "fileFormat": "JSON"
}
expected result is a scv file generated in "src\main\resources\employees.json"
with the content as follows:
[{"id":1,"firstName":"firstName1","lastname":"lastName1","address":"SQLITE"},
{"id":2,"firstName":"firstName2","lastname":"lastName2","address":"SQLITE"},
{"id":3,"firstName":"firstName3","lastname":"lastName3","address":"SQLITE"},
{"id":4,"firstName":"firstName4","lastname":"lastName4","address":"SQLITE"}]


