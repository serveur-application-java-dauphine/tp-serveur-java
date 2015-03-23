SELECT *
FROM Transaction t, Ordre o1, Ordre o2, Produit p
WHERE idOrderAchat = o1.idOrder
AND IdOrderVente = o2.idOrder
AND o1.idProduit = p.idProduit
AND (
o1.idPortefeuille =1
OR o2.idPortefeuille =1
)
