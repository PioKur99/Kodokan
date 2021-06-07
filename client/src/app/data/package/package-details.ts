import { Customer } from "../customer/customer"

export class PackageDetails {
    name: String
    prize: String
    CustomerList: Customer[]
    endDate: Date
    isPaid: boolean
    purchaseDate: Date
    entrance_used: number
    entrance_unused: number
    duration: number
}
