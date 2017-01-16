txt="curl --request GET http://localhost:8080/skiServ/skiStuffCRUD.jsp"
echo "GET all products"
echo $txt
read -p "Hit any key to continue..." -n 1 -r
echo ""
$txt
echo ""
read -p "Hit any key to continue..." -n 1 -r

echo ""
txt="curl --request GET http://localhost:8080/skiServ/show.jsp?id=1"
echo "GET all products"
echo $txt
read -p "Hit any key to continue..." -n 1 -r
echo ""
$txt
echo ""
read -p "Hit any key to continue..." -n 1 -r

echo ""
txt='curl --request POST --data "product=foo bar baz&category=one two three four&price=1234.56" -L  http://localhost:8080/skiServ/dataVerifier'
echo "POST a new product"
echo $txt
read -p "Hit any key to continue..." -n 1 -r
eval $txt
echo ""

echo ""
txt='curl --request POST --data "product=foo bar baz&category=one two three four&price=1234.56" -L http://localhost:8080/skiServ/dataVerifier'
echo "Try to POST the very same product again"
echo $txt
read -p "Hit any key to continue..." -n 1 -r
eval $txt
echo ""

echo ""
txt='curl --request POST --data "product=moe 12&category=abc&price=234AB.56" -L http://localhost:8080/skiServ/dataVerifier'
echo "Very bad data: product name and category are two short, bad price"
echo $txt
read -p "Hit any key to continue..." -n 1 -r
eval $txt
echo ""









