import { Customer } from "../customer/customer"

export class PackageDetails {
    name: String
    price: String
    endDate: string
    purchaseDate: string
    usedEntries: number
    remainingEntries: number
    duration: number
    owners: Customer[]
    paid: boolean

    end_date: Date
    purchase_date: Date
}
