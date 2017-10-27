meal = 42.50
tax = .0825
tip = .15
meal = meal + meal * tax
total = meal + meal * tip
diners = 2

print "Each person is responsible for $%.2f" % (total/2)